<template>
  <div class="container">
    <h3>All Appliances</h3>
    <div v-if="message" class="alert alert-success">
        {{ message }}
    </div>
     <div class="container">
      <table class="table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Last Service Date</th>
            <th>Next Service Date</th>
            <th>Details</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="appliance in appliances" :key="appliance.id">
            <td>{{ appliance.applianceName }}</td>
            <td>{{ appliance.lastServiceDate }}</td>
            <td>{{ appliance.nextServiceDate }}</td>
            <td>
                <button class="btn btn-info" v-on:click="updateApplianceClicked(appliance.id)">
                    View Details
                </button>
            </td>
            <td>
                <button class="btn btn-danger" v-on:click="deleteApplianceClicked(appliance.id, appliance.applianceName)">
                    Delete
                </button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="row">
          <button class="btn btn-primary" v-on:click="addApplianceClicked()">
              Add Appliance
          </button>
      </div>
    </div>
  </div>
</template>

<script>
import ApplianceDataService from '../services/ApplianceDataService';

export default {
  name: "ListAppliances",
  data() {
      return {
        appliances: [],
        message: null,
      };
  },
  methods: {
    getAllAppliances() {
      ApplianceDataService.getAllAppliances()
        .then(response => {
          this.appliances = response.data;
        });
    },
    deleteApplianceClicked(id, name) {
        ApplianceDataService.deleteAppliance(id)
          .then(response => {
              this.message = `${name} has been deleted`;
              this.getAllAppliances();
              console.log(response);
          });
    },
    updateApplianceClicked(id) {
        this.$router.push(`/appliance/${id}`)
    },
    addApplianceClicked() {
    this.$router.push(`/appliance/-1`);
    }
  },
  created() {
    this.getAllAppliances();
  }
};
</script>

<style>
</style>