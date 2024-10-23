import KeyboardReturnIcon from '@mui/icons-material/KeyboardReturn';
import { IconButton, StepLabel } from '@mui/material';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Step from '@mui/material/Step';
import Stepper from '@mui/material/Stepper';
import Typography from '@mui/material/Typography';
import { Fragment, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../api';
import { useNotification } from '../../hooks/useNotification';
import { Address } from '../../types/address';
import { Student } from '../../types/student';
import { AddressInformationStep } from './addressInformation';
import { BasicInformationStep } from './basicInformation';
import { ConfirmInformation } from './ConfirmInformation';
const steps = ['Informe seus dados', 'Informe seu endereço', 'Confirme seus dados'];





export default function RegisterStudent() {
    const { showNotification } = useNotification();
    const [user, setUser] = useState<Partial<Student>>({});
    const [address, setAddress] = useState<Partial<Address>>({});
    const handleChanges = (key: string, value: string | number) => {
        setUser((prev) => ({
            ...prev,
            [key]: value,
        }));
    }

    const handleChangeAddress = (key: string, value: string | number) => {
        setAddress((prev) => ({
            ...prev,
            [key]: value,
        }));
        user.address = address as Address;
        user.balance = 0;
        setUser(user);
    }

    const saveUser = () => {
        api.post('/students', user).then((response) => {
            showNotification({ message: "Estudante cadastrado com sucesso", type: "success" });
        }).catch((error) => {
            showNotification({ message: error.data, type: "error" });
        });
    }

    const [activeStep, setActiveStep] = useState(0);



    const handleNext = () => {


        setActiveStep((prevActiveStep) => prevActiveStep + 1);

        if (activeStep === steps.length - 1) {
            saveUser()
        }
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    const handleReset = () => {
        setActiveStep(0);
    };

    const navigate = useNavigate();

    return (
        <Box sx={{
            padding: "20px"
        }}>
            <Box sx={{ display: "flex" }}>
                <IconButton onClick={() => {
                    navigate("/login")
                }}>
                    <KeyboardReturnIcon />
                </IconButton>
            </Box>
            < Stepper activeStep={activeStep} >

                {
                    steps.map((label, index) => {
                        const stepProps: { completed?: boolean } = {};
                        const labelProps: {
                            optional?: React.ReactNode;
                        } = {};


                        return (
                            <Step key={label} {...stepProps}>
                                <StepLabel {...labelProps}>{label}</StepLabel>
                            </Step>
                        );
                    })
                }
            </Stepper>
            {activeStep === steps.length ? (
                <Fragment>
                    <Typography sx={{ mt: 2, mb: 1 }}>
                        All steps completed - you&apos;re finished
                    </Typography>
                    <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                        <Box sx={{ flex: '1 1 auto' }} />
                        <Button onClick={handleReset}>Reset</Button>
                    </Box>
                </Fragment>
            ) : (
                <Fragment>
                    <div style={{ height: "40vh" }}>
                        {
                            activeStep === 0 ? <BasicInformationStep onChange={handleChanges} /> :
                                activeStep === 1 ? <AddressInformationStep onChange={handleChangeAddress} address={address} /> :
                                    <ConfirmInformation student={user} />
                        }
                    </div>
                    <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                        <Button
                            color="inherit"
                            disabled={activeStep === 0}
                            onClick={handleBack}
                            sx={{ mr: 1 }}
                        >
                            Back
                        </Button>
                        <Box sx={{ flex: '1 1 auto' }} />

                        <Button onClick={handleNext}>
                            {activeStep === steps.length - 1 ? 'Finish' : 'Next'}
                        </Button>
                    </Box>
                </Fragment>
            )}
        </ Box>
    );
}