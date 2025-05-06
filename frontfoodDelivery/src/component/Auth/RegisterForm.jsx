import { Button, FormControl, InputLabel, MenuItem, Select, TextField, Typography } from '@mui/material'
import { Field, Form, Formik } from 'formik'
import React from 'react'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { registerUser } from '../State/Authentication/Action'

const initialValues={
    fullName:"",
    email:"",
    password:"",
    role:""
}

const RegisterForm = () => {
  const navigate=useNavigate()
  const dispatch=useDispatch()

  const handleSubmit=(values)=>{
     console.log("form values",values)
     dispatch(registerUser({userData:values,navigate}))
  }
  return (
     <div>
           <Typography variant='h5' className='text-center'>
              Register
           </Typography>
           <Formik onSubmit={handleSubmit} initialValues={initialValues}>
              <Form>
              <Field as={TextField} name="fullName" label="fullName"
                                 fullWidth variant="outlined" margin="normal" 
                                 sx={{
                                     '& .MuiOutlinedInput-root': {
                                       '& fieldset': {
                                         borderColor: 'darkblue', 
                                       },
                                       '&:hover fieldset': {
                                         borderColor: 'darkblue', 
                                       },
                                       '&.Mui-focused fieldset': {
                                         borderColor: 'darkblue', 
                                       },
                                     },
                                     '& .MuiInputLabel-root': {
                                       color: 'white',
                                     },
                                     '& .MuiInputLabel-root.Mui-focused': {
                                       color: 'white',
                                     },
                                     input: {
                                       color: 'white',
                                       backgroundColor: '#1e1e1e',
                                     },
                                   }}/>
                 <Field as={TextField} name="email" label="email"
                                 fullWidth variant="outlined" margin="normal" 
                                 sx={{
                                     '& .MuiOutlinedInput-root': {
                                       '& fieldset': {
                                         borderColor: 'darkblue', 
                                       },
                                       '&:hover fieldset': {
                                         borderColor: 'darkblue', 
                                       },
                                       '&.Mui-focused fieldset': {
                                         borderColor: 'darkblue', 
                                       },
                                     },
                                     '& .MuiInputLabel-root': {
                                       color: 'white',
                                     },
                                     '& .MuiInputLabel-root.Mui-focused': {
                                       color: 'white',
                                     },
                                     input: {
                                       color: 'white',
                                       backgroundColor: '#1e1e1e',
                                     },
                                   }}/>
                 <Field as={TextField} name="password" label="password"
                                 fullWidth variant="outlined" margin="normal" type="password"
                                 sx={{
                                     '& .MuiOutlinedInput-root': {
                                       '& fieldset': {
                                         borderColor: 'darkblue', 
                                       },
                                       '&:hover fieldset': {
                                         borderColor: 'darkblue', 
                                       },
                                       '&.Mui-focused fieldset': {
                                         borderColor: 'darkblue', 
                                       },
                                     },
                                     '& .MuiInputLabel-root': {
                                       color: 'white',
                                     },
                                     '& .MuiInputLabel-root.Mui-focused': {
                                       color: 'white',
                                     },
                                     input: {
                                       color: 'white',
                                       backgroundColor: '#1e1e1e',
                                     },
                                   }}/>
              <FormControl fullWidth>
  <InputLabel id="demo-simple-select-label" sx={{
      color: 'white',
      '&.Mui-focused': {
        color: 'white', 
      },
    }}>
    Role
  </InputLabel>
  <Field
    as={Select}
    labelId="demo-simple-select-label"
    id="demo-simple-select"
    name="role"
    label="Role"
    sx={{
      color: 'white', 
      backgroundColor: '#1e1e1e', 
      '.MuiOutlinedInput-notchedOutline': {
        borderColor: 'darkblue', 
      },
      '&:hover .MuiOutlinedInput-notchedOutline': {
        borderColor: 'darkblue', 
      },
      '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
        borderColor: 'darkblue', 
      },
      '.MuiSelect-icon': {
        color: 'white', 
      },
    }}
  >
    <MenuItem value={"ROLE_CUSTOMER"}
        sx={{
          backgroundColor: '#1e1e1e',
          color: 'white',             
          '&:hover': {
            backgroundColor: '#333',  
          },
          '&.Mui-selected': {
            backgroundColor: '#333',  
          },
          '&.Mui-selected:hover': {
            backgroundColor: '#444', 
          },
        }}>Customer</MenuItem>
    <MenuItem value={"ROLE_RESTAURANT_OWNER"}
         sx={{
          backgroundColor: '#1e1e1e', 
          color: 'white',             
          '&:hover': {
            backgroundColor: '#333',  
          },
          '&.Mui-selected': {
            backgroundColor: '#333',  
          },
          '&.Mui-selected:hover': {
            backgroundColor: '#444', 
          },
        }}>Restaurant Owner</MenuItem>
  </Field>
</FormControl>

                      <Button fullWidth type='submit' variant='contained' 
                         sx={{ 
                             backgroundColor: "#0039CB",  
                             color: "#ffffff",
                             margin: "1rem 0rem",
                             '&.Mui-disabled': {
                               backgroundColor: "#0039CB",
                               color: "#ffffff",
                               opacity: 0.8,
                             }
                           }}>Register</Button>
              </Form>
           </Formik>
           <Typography variant='body2' align='center' sx={{mt:3}} >
              If have an account already?
              <Button size='small' onClick={()=>navigate("/account/login")}
                   sx={{color: "#0039CB", ml:1}} >
                   login
              </Button>
           </Typography>
         </div>
  )
}

export default RegisterForm
