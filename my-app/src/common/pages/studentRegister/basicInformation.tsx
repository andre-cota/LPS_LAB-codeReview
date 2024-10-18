import { Visibility, VisibilityOff } from '@mui/icons-material';
import { FormControl, IconButton, InputAdornment, InputLabel, OutlinedInput, TextField } from '@mui/material';
import Grid from '@mui/material/Grid2';
import React, { useState } from 'react';
export interface BasicInformationStepProps {
    onChange: (key: string, value: string | number) => void;
}
export const BasicInformationStep = ({ onChange }: BasicInformationStepProps) => {
    const [showPassword, setShowPassword] = useState(false);

    const handleClickShowPassword = () => setShowPassword((show) => !show);

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };

    const handleMouseUpPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };
    return (
        <Grid container spacing={2} marginTop={5}>
            <Grid size={6}>
                <TextField
                    required
                    id="nome"
                    label="Nome"
                    fullWidth
                    onChange={(e) => {
                        onChange('name', e.target.value);
                    }}
                />
            </Grid>
            <Grid size={6}>
                <TextField
                    required
                    id="rg"
                    label="RG"
                    fullWidth
                    onChange={(e) => {
                        onChange('rg', e.target.value);
                    }}
                />
            </Grid>
            <Grid size={6}>
                <TextField
                    required
                    id="course"
                    label="Curso"
                    fullWidth
                    onChange={(e) => {
                        onChange('course', e.target.value);
                    }}
                />
            </Grid>
            <Grid size={6}>
                <TextField
                    required
                    id="email"
                    label="Email"
                    fullWidth
                    onChange={(e) => {
                        onChange('email', e.target.value);
                    }}
                />
            </Grid>
            <Grid size={8}>
                <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
                    <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
                    <OutlinedInput
                        id="outlined-adornment-password"
                        type={showPassword ? 'text' : 'password'}
                        endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword}
                                    onMouseDown={handleMouseDownPassword}
                                    onMouseUp={handleMouseUpPassword}
                                    edge="end"
                                >
                                    {showPassword ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                        }
                        label="Password"
                        onChange={(e) => {
                            onChange('password', e.target.value);
                        }}
                    />
                </FormControl>
            </Grid>
        </Grid>

    )
}