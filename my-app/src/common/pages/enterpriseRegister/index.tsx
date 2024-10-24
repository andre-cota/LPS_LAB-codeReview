import React, { useState } from 'react';
import { TextField, Button, Typography } from '@mui/material';
import styled from 'styled-components';
import api from '../../api';
import { useNotification } from '../../hooks/useNotification';
import GoBackIcon from '../../components/GoBackIcon';

interface FormValues {
  name: string;
  email: string;
  password: string;
  confirmPassword: string;
}

interface Errors {
  name: string;
  email: string;
  password: string;
  confirmPassword: string;
}

const EnterpriseRegister: React.FC = () => {
  const [formValues, setFormValues] = useState<FormValues>({
    name: '',
    email: '',
    password: '',
    confirmPassword: '',
  });

  const [errors, setErrors] = useState<Errors>({
    name: '',
    email: '',
    password: '',
    confirmPassword: '',
  });

  const { showNotification } = useNotification();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };

  const validate = () => {
    let tempErrors: Errors = { ...errors };
    tempErrors.name = formValues.name ? '' : 'Nome é obrigatório';
    tempErrors.email = /^[^@\s]+@[^@\s]+\.[^@\s]+$/.test(formValues.email) ? '' : 'E-mail inválido';
    tempErrors.password = formValues.password.length >= 6 ? '' : 'A senha deve ter no mínimo 6 caracteres';
    tempErrors.confirmPassword =
      formValues.password === formValues.confirmPassword ? '' : 'As senhas não coincidem';

    setErrors(tempErrors);
    return Object.values(tempErrors).every((x) => x === '');
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (validate()) {
      api.post('/companies', formValues).then((response) => {
          showNotification({ message: "Empresa cadastrada com sucesso", type: "success" });
      }).catch((error) => {
          showNotification({ message: 'Erro ao tentar cadastrar empresa', type: "error" });
      });
    }
  };

  return (
    <FormContainer onSubmit={handleSubmit}>
      <GoBackIconDiv>
        <GoBackIcon link='/login'/>
      </GoBackIconDiv>
      <Title>
        Registrar Empresa
      </Title>
      <div style={{display: 'flex', justifyContent: 'space-between', gap: 30, height: '35vh'}}>
        <div>
          <StyledTextField
            label="Nome"
            name="name"
            value={formValues.name}
            onChange={handleChange}
            error={!!errors.name}
            helperText={errors.name}
            fullWidth
            required
          />
          <StyledTextField
            label="E-mail"
            name="email"
            value={formValues.email}
            onChange={handleChange}
            error={!!errors.email}
            helperText={errors.email}
            fullWidth
            required
          />
        </div>
        <div>
          <StyledTextField
            label="Senha"
            type="password"
            name="password"
            value={formValues.password}
            onChange={handleChange}
            error={!!errors.password}
            helperText={errors.password}
            fullWidth
            required
          />
          <StyledTextField
            label="Confirmação de Senha"
            type="password"
            name="confirmPassword"
            value={formValues.confirmPassword}
            onChange={handleChange}
            error={!!errors.confirmPassword}
            helperText={errors.confirmPassword}
            fullWidth
            required
          />
        </div>
      </div>
      <Footer>
        <Button onSubmit={handleSubmit} type={'submit'}>
          Finish
        </Button>
      </Footer>
    </FormContainer>
  );
};

export default EnterpriseRegister;

// Styled Components
const Title = styled.h1`
  text-align: center;
  margin-bottom: 50px;
  font-size: 1.5em;
  font-weight: normal;
`;  

const FormContainer = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  margin: 40px auto;
  padding: 20px;
  box-sizing: border-box;

`;

const StyledTextField = styled(TextField)`
  && {
    margin-bottom: 16px;
  }
`;

const StyledButton = styled(Button)`
  && {
    margin-top: 20px;
  }
`;

const Footer = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: end;
  margin-top: 20px;
  width: 100%;
`;

const GoBackIconDiv = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  width: 100%;
`; 
