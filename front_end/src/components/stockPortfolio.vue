<template>
    <v-form>
        <v-container class="grey lighten-5 identifier">
            <h2>Add/Update Stock</h2>
            <v-col cols="2" class="inputs">

                <v-row class="rowInputs">
                    <v-text-field label = "Ticker" outlined v-model="ticker" @keyup.enter="addItem"> </v-text-field>
                </v-row>

                <v-row class="rowInputs">
                    <v-text-field label = "Average Price" outlined v-model="avg_price" @keyup.enter="addItem"> </v-text-field>
                </v-row>

                <v-row class="rowInputs">
                    <v-text-field label = "Number of Shares" outlined v-model="num_shares" @keyup.enter="addItem"> </v-text-field>
                </v-row>

                <v-row class="rowInputs">
                    <v-btn color="primary" elevation="5" @click.native="addStock">Add</v-btn>
                </v-row>

            </v-col>
        </v-container>
<!--         <p> {{ this.info }} </p> -->
    </v-form>

</template>

<script>
import axios from 'axios';
export default {
     mounted(){
        axios.get('http://localhost:8080/stocks')
        .then(response =>{
            this.info = response.data;
            console.log(this.info);
        })
        .catch(error => console.log(error));
    },

    methods: {
        addStock: function(){
            const postData={
                ticker: this.ticker,
                avgPrice: this.avg_price,
                numShares: this.num_shares
            }
            axios.post('http://localhost:8080/stocks', postData)
            .then(response =>{
                console.log(response);
            })
            .catch(error => console.log(error));
            
        }
    }
    
};

</script>


<style scoped>
    .identifier {
        float:left;
        max-width:25%;
        padding-bottom: 1.5%;
    }
    .inputs{
        max-width:100%;
    }
    .rowInputs{
        margin-bottom: -7%;
    }

</style>