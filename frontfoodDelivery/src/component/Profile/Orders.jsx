import React, { useEffect } from 'react'

import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { getUsersOrders } from '../State/Order/Action'
import OrderCard from './OrderCard'

const Orders = () => {
  const {auth,cart,order} = useSelector(store=>store)
  const navigate=useNavigate()
  const jwt = localStorage.getItem("jwt")
  const dispatch = useDispatch();

  useEffect(()=>{
     dispatch(getUsersOrders(jwt))
  },[auth.jwt])

  console.log("Fetched orders:", order.orders);

  return (
    <div className='flex items-center flex-col'>
       <h1 className='text-xl text-center py-7 font-semibold'>My Orders</h1>
       <div className='space-y-5 w-full lg:w-1/2'>
       {
  Array.isArray(order?.orders) &&
  order.orders.map((orderObj, index) => (
    orderObj.item?.map((item, i) => (
      <OrderCard key={`${index}-${i}`} item={item} order={orderObj} />
    ))
  ))
}
       </div>
    </div>
  )
}

export default Orders
