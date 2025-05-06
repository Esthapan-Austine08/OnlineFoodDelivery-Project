import { Card, CardActions, CardContent, CardMedia, IconButton, Typography } from '@mui/material'
import React from 'react'
import DeleteIcon from '@mui/icons-material/Delete';

const EventCard = () => {
  return (
    <div>
      <Card sx={{width:300}}>
        <CardMedia sx={{height:345}}
          image='https://images.pexels.com/photos/2097090/pexels-photo-2097090.jpeg?auto=compress&cs=tinysrgb&w=600'/>
        <CardContent>
            <Typography variant='h5'>
                Arabian Food
            </Typography>
            <Typography variant='body2'>
                30% off on your first order
            </Typography>
            <div className='py-2 space-y-2'>
                <p>{"Delhi"}</p>
                <p className='text-sm text-blue-500'>March 25, 2025 12:00 AM</p>
                <p className='text-sm text-red-500'>April 25, 2025 12:00 AM</p>
            </div>
        </CardContent>
          { false &&
            <CardActions>
                <IconButton>
                    <DeleteIcon/>
                </IconButton>
            </CardActions>
          }
      </Card>
    </div>
  )
}

export default EventCard
