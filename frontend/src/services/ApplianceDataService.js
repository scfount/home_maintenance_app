import axios from "axios";

const API_URL = "http://localhost:8080";

class ApplianceDataService {

    getAllAppliances() {
        return axios.get(`${API_URL}/appliances`);
    }

    updateAppliance(id, appliance) {
        return axios.put(`${API_URL}/${id}`, appliance);
    }
  
    createAppliance(appliance) {
        return axios.post(`${API_URL}/appliance`, appliance);
    }

    deleteAppliance(id) {
        return axios.delete(`${API_URL}/delete/${id}`);
    }

    retrieveAppliance(id) {
        return axios.get(`${API_URL}/appliance/${id}`);
    }
}

export default new ApplianceDataService();