import React, { useEffect, useState } from 'react'
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import { Line } from "react-chartjs-2";
import axios from 'axios';
import { registerables , Chart } from "chart.js";

Chart.register(...registerables);
interface data {
    timestamp: string;
    value: string
}

interface props  {
    filterValue: string
}

const FirstComponent = (props: props) => {
    useEffect(() => {
        getData()
    }, [props.filterValue]);
    const [chartData, setChartData] = useState({
        labels: [], 
        datasets: [
          {
            label: "Users Gained ",
            data: [],
            backgroundColor: [
              "rgba(75,192,192,1)",
              "#ecf0f1",
              "#50AF95",
              "#f3ba2f",
              "#2a71d0"
            ],
            borderColor: "black",
            borderWidth: 2
          }
        ]
      });

    const options = ["posX", "posY","velX","velY","confidence"]
    const getData = () => {
        axios.get(`http://localhost:8080/data/${props.filterValue}`).then(data => {
            setChartData({
                labels: data.data.response.map((data: data) => data.timestamp), 
                datasets: [
                {
                    label: "Users Gained ",
                    data: data.data.response.map((data: data) => parseFloat(data.value)),
                    backgroundColor: [
                    "rgba(75,192,192,1)",
                    "#ecf0f1",
                    "#50AF95",
                    "#f3ba2f",
                    "#2a71d0"
                    ],
                    borderColor: "black",
                    borderWidth: 2
                }
                ]
            })
        })
      }
      
  return (
    <div>

        <div className="chart-container">
        <h2 style={{ textAlign: "center" }}>Chart</h2>
        <Line
            data={chartData}
            options={{
            plugins: {
                title: {
                display: true,
                text: "Users Gained between 2016-2020"
                }
            }
            }}
      />
    </div>
    
    </div>
    
  )

  
}

export default FirstComponent