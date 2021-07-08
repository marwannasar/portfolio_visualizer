<template>
    <div>
        <v-container class="addStockContainer">
            <h2 class="addStockTitle">Add/Update Stock</h2>
            <v-col cols="2" class="colInputs">

                <v-row class="rowInputs">
                    <v-text-field label = "Ticker" outlined background-color="white" v-model="ticker" @keyup.enter="addItem"> </v-text-field>
                </v-row>

                <v-row class="rowInputs">
                    <v-text-field label = "Average Price" outlined background-color="white" v-model="avg_price" @keyup.enter="addItem"> </v-text-field>
                </v-row>

                <v-row class="rowInputs">
                    <v-text-field label = "Number of Shares" outlined background-color="white" v-model="num_shares" @keyup.enter="addItem"> </v-text-field>
                </v-row>

                <v-row class="addButton">
                    <v-btn block dark color="black" elevation="2" @click.native="addStock">Add</v-btn>
                </v-row>
            </v-col>

            <v-simple-table fixed-header>
            <template v-slot:default>
                <thead>
                    <tr>
                        <th class="text-left">
                            Ticker
                        </th>
                        <th class="text-left">
                            Average Price
                        </th>
                            <th class="text-left">
                            Number of Shares
                        </th>
                        <th class="text-center">
                            <!-- # -->
                        </th> 
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(stock,index) in stocks" :key="index">
                        <td> {{stock.tick}} </td>
                        <td> {{stock.avgP}} </td>
                        <td> {{stock.numS}} </td>
                        <td>
                            <div class="text-center" @click="deleteStock(index,stock.tick)">
                                <span class="fa fa-trash"></span>
                            </div>
                            
                        </td>
                    </tr>
                </tbody>
            </template>
            </v-simple-table>

        </v-container>


        <div class="pieChart">
            <apexchart type="pie" width="660" :options="chartOptions" :series="series"></apexchart>
        </div>

    </div>
</template>

<script>
import axios from 'axios';
export default {
     mounted(){
         this.getAndListAllStocks();
    },

    methods: {
        addStock: function(){
            if (this.ticker.trim().length === 0) return;
            if (this.avg_price.trim().length === 0) return;
            if (this.num_shares.trim().length === 0) return;

            const postData={
                ticker: this.ticker.trim(),
                avgPrice: this.avg_price.trim(),
                numShares: this.num_shares.trim()
            }

            axios.post('http://localhost:8080/stocks', postData).then(() =>{
                axios.get('http://localhost:8080/stocks/' + postData.ticker)
                .then(response =>{
                    //console.log(response);
                    postData.avgPrice = response.data[0].avgPrice.toFixed(2);
                    postData.numShares = response.data[0].numShares.toFixed(2);
    
                    //console.log(postData.avgPrice, postData.numShares);

                    this.stocks.forEach(function(element,index,object){
                        //console.log(element);
                        //console.log(postData.ticker, element.tick);
                        if (element.tick === postData.ticker){
                            console.log("found match (updating stock)");
                            object.splice(index,1);
                        }
                    });

                    this.stocks.push({
                        tick: postData.ticker,
                        avgP: postData.avgPrice,
                        numS: postData.numShares
                    })

                    this.chartOptions.labels.push(postData.ticker)
                    this.series.push((postData.numShares * postData.avgPrice))
                    
                })
                .catch(error => console.log(error))
            })
            
            .catch(error => console.log(error))
            this.ticker = '';
            this.avg_price = '';
            this.num_shares = '';
        },

        deleteStock(index,ticker){
            axios.delete('http://localhost:8080/stocks/' + ticker);
            this.stocks.splice(index,1);
        },

        getAndListAllStocks: function(){

            const getData={
                ticker: this.ticker.trim(),
                avgPrice: this.avg_price.trim(),
                numShares: this.num_shares.trim()
            }

            axios.get('http://localhost:8080/stocks')
            .then(response => {
                for (let i = 0; i < response.data.length; i++){
                    getData.ticker = response.data[i].ticker;
                    getData.avgPrice = response.data[i].avgPrice.toFixed(2);
                    getData.numShares = response.data[i].numShares.toFixed(2);
                    
                    this.stocks.push({
                        tick: getData.ticker,
                        avgP: getData.avgPrice,
                        numS: getData.numShares
                    })

                    this.chartOptions.labels.push(getData.ticker)
                    this.series.push((getData.numShares * getData.avgPrice))

                    //console.log(this.chartOptions.labels[i]);
                    //console.log(this.series[i]);

                }

            })
            
        }
    },

    data(){

        return{
            ticker:'',
            avg_price:'',
            num_shares:'',
            stocks: [],

            series: [], // totalInvested as doubles
            chartOptions: {
                labels: [], //ticker names
                responsive: [{
                breakpoint: 480,
                options: {
                    chart: {
                    width: 200
                    },
                    legend: {
                    position: 'bottom'
                    }
                }
                }]
            }

        }

    }
    
};

</script>


<style scoped>
    .addStockContainer {
        float:left;
        max-width:30%;
        padding-bottom: 1.5%;
        margin-left: 2em;
        min-height: 100%;
        position:absolute;
        background-color: #FCFCFC;
    }
    .colInputs{
        max-width:100%;
    }
    .rowInputs{
        margin-bottom: -6.5%;
    }
    .addButton{
        margin-bottom: 3%;
    }
    .pieChart{
        float:right;
    }
    .addStockTitle{
        font-family: 'Roboto', sans-serif;
        font-weight: bold;
    }
    
</style>