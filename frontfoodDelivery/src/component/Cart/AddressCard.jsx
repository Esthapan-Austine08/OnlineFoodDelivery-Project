import { Button, Card } from '@mui/material'
import React from 'react'
import HomeIcon from '@mui/icons-material/Home';

const AddressCard = ({item,showButton,handleSelectAddress}) => {
  return (
    <Card className='flex gap-5 w-75 p-5'>
       <HomeIcon/>
     <div className='space-y-2 text-gray-500'>
        <h1 className='font-semibold text-lg text-white'>Home</h1>
       <p>
        Srikush,new rogsguim building,98000,Bhopal,Pune
       </p>
       {showButton && (
          <Button variant='outlined' fullWidth onClick={()=>handleSelectAddress(item)}
          sx={{
            color: 'blue',
            borderColor: 'blue',
            '&:hover': {
              borderColor: 'darkblue',
              backgroundColor: 'rgba(0, 0, 255, 0.04)',
            },
          }} >select</Button>
       ) }
     </div>
    </Card>
  )
}

export default AddressCard
