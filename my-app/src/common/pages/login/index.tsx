import React, { useState } from 'react';
import styled from 'styled-components';
import {
  Button,
  TextField,
  IconButton,
  InputAdornment,
  Typography,
  Box
} from '@mui/material';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import api from '../../api';
import { useNotification } from '../../hooks/useNotification';
import { useNavigate } from 'react-router';

const LoginContainer = styled(Box)`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 4rem;
  max-width: 400px;
  margin: auto;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 300px;
  `;

const StyledTextField = styled(TextField)`
  margin-bottom: 1rem !important;
`;

const LoginButton = styled(Button)`
  margin-top: 1rem !important;
`;

const Login: React.FC = () => {
  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [showPassword, setShowPassword] = useState<boolean>(false);

  const navigate = useNavigate();
  const { showNotification } = useNotification();

  const handlePasswordVisibilityToggle = () => {
    setShowPassword((prev) => !prev);
  };

  const handleLogin = () => {
      api.post('/auth/login', {email, password}).then((response) => {
          navigate('/dashboard');
          //showNotification({ message: "Empresa cadastrada com sucesso", type: "success" });
      }).catch((error) => {
          showNotification({ message: 'Erro ao tentar realizar o login', type: "error" });
      });
  };

  return (
    <LoginContainer>
      <Typography variant="h5" component="h1" gutterBottom>
        Login
      </Typography>
      <StyledTextField
        label="Email"
        type="email"
        fullWidth
        variant="outlined"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <StyledTextField
        label="Senha"
        type={showPassword ? 'text' : 'password'}
        fullWidth
        variant="outlined"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        InputProps={{
          endAdornment: (
            <InputAdornment position="end">
              <IconButton onClick={handlePasswordVisibilityToggle} edge="end">
                {showPassword ? <VisibilityOff /> : <Visibility />}
              </IconButton>
            </InputAdornment>
          ),
        }}
      />
      <LoginButton
        variant="contained"
        color="primary"
        fullWidth
        onClick={handleLogin}
      >
        Entrar
      </LoginButton>
    </LoginContainer>
  );
};

export default Login;
