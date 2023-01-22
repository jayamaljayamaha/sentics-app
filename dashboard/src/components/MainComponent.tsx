import React, { useState } from 'react'
import FirstComponent from './FirstComponent'
import Dropdown from 'react-dropdown';

function MainComponent() {
    const options = ["posX", "posY","velX","velY","confidence"]
    const [filterValue, setFilterValue] = useState("posX");
  return (
    <div>
        <div className='dropdown'>
        <Dropdown options={options} 
            onChange={(arg) => setFilterValue(arg.value)} 
            value={filterValue} 
            placeholder="Select an option" />
    </div>
    <FirstComponent filterValue={filterValue}/>
    </div>
    
  )
}

export default MainComponent