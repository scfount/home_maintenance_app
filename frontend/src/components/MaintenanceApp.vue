<template>
    <div>
        <h1>Home Maintenance Application</h1>
        <ListAppliances />
    </div>
</template>

<script>
import ListAppliances from "./ListAppliances";

export default {
    name: "MaintenanceApp",
    components: {
    ListAppliances
  },
}
</script>

<style scoped>

</style>