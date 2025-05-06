//MenuCard.jsx

import { Accordion, AccordionDetails, AccordionSummary, Button, Checkbox, FormControlLabel, FormGroup } from '@mui/material'
import React, { useState } from 'react'
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { Category } from '@mui/icons-material';
import { categorizeIngredients } from '../util/categorizeIngredients';
import { addItemToCart } from '../State/Cart/Action';
import { useDispatch } from 'react-redux';

const ingredient=[
    {
        category:"Nuts & Seeds",
        ingredients:["Cashews"]
    },
    {
        category:"Protien",
        ingredients:["Protein","Bacon"]
    },
    {
        category:"Bread",
        ingredients:["Hamburger buns"]
    },
    {
        category:"Vegitables",
        ingredients:["Onion Slice","Lettuce","Pickles","Tomato Slices"]
    },
    {
        category:"Condiment",
        ingredients:["Ketchup"]
    }
]  


const MenuCard = ({item}) => {
  const [selectedIngredients,setSelectedIngredients]=useState([])
  const dispatch=useDispatch();

     const handleCheckBocChange=(itemName)=>{
         console.log("value",itemName)
       if(selectedIngredients.includes(itemName)){
        setSelectedIngredients(
            selectedIngredients.filter((item) => item !== itemName)
        )
       }
       else{
          setSelectedIngredients([...selectedIngredients,itemName]);
       }
     }

  const handleAddItemToCart=(e)=>{
     e.preventDefault();
    const reqData={
       token:localStorage.getItem("jwt"),
       cartItem:{
         foodId:item.id,
         quantity:1,
         ingredients:selectedIngredients,
       },
    }
     dispatch(addItemToCart(reqData))
     console.log("req data",reqData)
  }




  return (
    <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel2-content"
          id="panel2-header"
        >
          <div className='lg:flex flex items-center justify-center'>
            <div className='lg:flex items-center lg:gap-5'>
               <img className='w-[7rem] h-[7rem] object-cover'
               src={item.images[0]} alt=''/>
              <div className='space-y-1 lg:space-y-5 lg:max-w-2xl'>
                <p className='font-semibold text-xl'>{item.name}</p>
                <p>₹{item.price}</p>
                <p className='text-gray-400'>{item.description}</p>
              </div>
            </div>
          </div>

        </AccordionSummary>
        <AccordionDetails>
          <form onSubmit={handleAddItemToCart}>
            <div className='flex gap-5 flex-wrap'>
               {
                Object.keys(categorizeIngredients(item.ingredients)).map((category)=>
                  <div>
                    <p>{category}</p>
               <FormGroup>
    {categorizeIngredients(item.ingredients)[category].map((item)=> (<FormControlLabel key={item.id} control={<Checkbox 
             onChange={()=>handleCheckBocChange(item.name)}
             sx={{
              color: '#0033cc', 
              '&.Mui-checked': {
                color: '#0033cc',
              },
            }}
     />
} label={item.name} />
               )
             )}     
              </FormGroup>       
                  </div>
                )
               }
            </div>
            <div className='pt-5'>
              <Button  variant='contained' disabled={false} type='submit'
                 sx={{
                  backgroundColor: '#0033cc', 
                  '&:hover': {
                    backgroundColor: '#002db3',
                  },
                }} >
                {true?"Add to Cart":"Out of Stock"}
              </Button>
            </div>
          </form>
        </AccordionDetails>
      </Accordion>
  )
}

export default MenuCard
