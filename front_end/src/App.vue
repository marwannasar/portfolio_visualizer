<template>
  <v-app>
    <v-app-bar dark color="black" dense app>
      <v-toolbar-title >Portfolio Visualizer</v-toolbar-title>
    </v-app-bar>

    <v-content>
      <stockPortfolio />
    </v-content>

  </v-app>
</template>

<script>

 import stockPortfolio from './components/stockPortfolio.vue' 

export default {
  name: 'App',
  components: {
     stockPortfolio 
  }

}
</script>

