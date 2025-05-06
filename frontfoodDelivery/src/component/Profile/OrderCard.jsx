import { Button, Card } from '@mui/material'
import React from 'react'

const OrderCard = ({item,order}) => {
  return (
    <Card className='flex justify-between items-center p-5'>
      <div className='flex items-center space-x-5'>
         <img className='h-16 w-16'
          src={item.food.images[0]} alt=''/>
          <div>
            <p>{item.food.name}</p>
            <p>₹{item.totalPrice}</p>
          </div>
      </div>
      <div>
        <Button disabled className='cursor-not-allowed'
              sx={{ 
                backgroundColor: "#0039CB",  
                color: "#ffffff",
                margin: "2rem 0rem",
                '&.Mui-disabled': {
                  backgroundColor: "#0039CB",
                  color: "#ffffff",
                  opacity: 0.8,
                }
              }} >{order.orderStatus}</Button>
      </div>
    </Card>
  )
}

export default OrderCard
