<template>
  <div>
   <h3>Appliance Details</h3>
    <div class="container">
      <form @submit="validateAndSubmit">
          <div v-if="errors.length">
              <div
              class="alert alert-warning" 
              v-bind:key="index" 
              v-for="(error, index) in errors">{{error}}</div>
          </div>
        <fieldset class="form-group">
          <label>Id</label>
          <input type="text" class="form-control" v-model="id" disabled>
        </fieldset>
        <fieldset class="form-group">
            <label for="appliance-name">Appliance Name</label>
            <input type="text" class="form-control" v-model="applianceName">
        </fieldset>
        <fieldset class="form-group">
            <label for="service-frequency">Service Frequency (months)</label>
            <input type="number" class="form-control" v-model.number="serviceFrequency">
        </fieldset>
        <fieldset class="form-group">
            <label for="last-date">Last Service Date</label>
            <!-- <v-date-picker :masks="{ input: 'MMMM D YYYY' }" v-model="lastServiceDate"/> -->
            <input type="text" class="form-control" v-model="lastServiceDate">
        </fieldset>
        <fieldset class="form-group">
            <label for="next-service-date">Next Service Date</label>
            <!-- <v-date-picker :masks="{ input: 'MMMM D YYYY' }" v-model="nextServiceDate"/> -->
            <input type="text" class="form-control" v-model="nextServiceDate">
        </fieldset>
        <fieldset class="form-group">
            <label for="appliance-link">Appliance Link</label>
            <input type="text" class="form-control" v-model="applianceLink">
        </fieldset>
        <fieldset class="form-group">
          <label>Service Description</label>
          <textarea name="description" id="description" cols="30" rows="5" class="form-control" v-model="description"></textarea>
        </fieldset>
        <button class="btn btn-success" type="submit">Save</button>
      </form>
    </div>
  </div>
</template>

<script>
import ApplianceDataService from '../services/ApplianceDataService';

export default {
  name: "applianceDetails",
  data() {
    return {
        applianceName: "",
        serviceFrequency: "",
        lastServiceDate: "",
        nextServiceDate: "",
        applianceLink: "",
        description: "",
        stringDate: "",
        stringArray: "",
        errors: [],
        
    };
  },
  computed: {
      id() {
          return this.$route.params.id;
      },
      
  },
  methods: {
      refreshApplianceDetails() {
          ApplianceDataService.retrieveAppliance(this.id)
          .then(response => {
              this.applianceName = response.data.applianceName;
              this.serviceFrequency = response.data.serviceFrequency;
              this.lastServiceDate = response.data.lastServiceDate;
              this.nextServiceDate = response.data.nextServiceDate;
              this.applianceLink = response.data.applianceLink;
              this.description = response.data.description;
          });
      },
      validateAndSubmit(e) {
        e.preventDefault();
        this.errors = [];
        if (!this.applianceName) {
            this.errors.push("Enter appliance name");
        } 
        if (this.description.length < 2) {
            this.errors.push("You'll probably need to do more work than that ;)");
        }
        if (!this.serviceFrequency) {
            this.errors.push("Enter a service frequency");
        }
        else if (!this.lastServiceDate) {
            this.errors.push("Enter a last service date");
        }
        // this.lastServiceDate = this.parseDate(this.lastServiceDate);
        // this.nextServiceDate = this.parseDate(this.nextServiceDate);
        // this.calculateNextServiceDate();
        if (this.errors.length === 0) {
            if (this.id === -1) {
                ApplianceDataService.createAppliance({
                    applianceName: this.applianceName,
                    serviceFrequency: this.serviceFrequency,
                    lastServiceDate: this.lastServiceDate,
                    nextServiceDate: this.nextServiceDate,
                    applianceLink: this.applianceLink,
                    description: this.description
                })
                .then(() => {
                    this.$router.push('/appliances');
                });
            } else {
                ApplianceDataService.updateAppliance(this.id, {
                    id: this.id,
                    applianceName: this.applianceName,
                    serviceFrequency: this.serviceFrequency,
                    lastServiceDate: this.lastServiceDate,
                    nextServiceDate: this.nextServiceDate,
                    applianceLink: this.applianceLink,
                    description: this.description
                })
                .then(() => {
                    this.$router.push('/appliances');
                });
            }
        }
    },
    // calculateNextServiceDate: function (date) {
    //     let str = date.toString();
    //     let strArray = str.split(" ");

    //     let startMonth = parseInt(strArray[2]);

    //     this.nextServiceDate = this.lastServiceDate;
    //     return this.nextServiceDate;
    //   },
    // parseDate: function (date) {
    //     this.stringDate = date.toString();
    //     this.stringArray = this.stringDate.split(" ");
    //     return this.stringArray[1] + " " + this.stringArray[2] + " " + this.stringArray[3];
    //   },
    },
  created() {
      this.refreshApplianceDetails();
  }
};
</script>

<style>
</style>