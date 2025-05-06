
import { Button, TextField, Typography } from '@mui/material'
import { Field, Form, Formik } from 'formik'
import React from 'react'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { loginUser } from '../State/Authentication/Action'


const initialValues={
        email:"",
        password:""
}
const LoginForm = () => {
    const navigate=useNavigate()
    const dispatch = useDispatch()

    const handleSubmit=(values)=>{
        dispatch(loginUser({userData:values,navigate}))   
    }
  return (
    <div>
      <Typography variant='h5' className='text-center'>
         Login
      </Typography>
      <Formik onSubmit={handleSubmit} initialValues={initialValues}>
         <Form>
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
                            fullWidth variant="outlined" margin="normal"
                            sx={{
                                '& .MuiOutlinedInput-root': {
                                  '& fieldset': {
                                    borderColor: 'darkblue', // normal
                                  },
                                  '&:hover fieldset': {
                                    borderColor: 'darkblue', // hover
                                  },
                                  '&.Mui-focused fieldset': {
                                    borderColor: 'darkblue', // focus
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
                      }}>Login</Button>
         </Form>
      </Formik>
      <Typography variant='body2' align='center' sx={{mt:3}} >
         Don't have an account?
         <Button size='small' onClick={()=>navigate("/account/register")}
              sx={{color: "#0039CB", ml:1}} >
              register
         </Button>
      </Typography>
    </div>
  )
}

export default LoginForm
