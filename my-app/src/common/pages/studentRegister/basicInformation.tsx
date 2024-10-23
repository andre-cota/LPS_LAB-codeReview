import { Visibility, VisibilityOff } from '@mui/icons-material';
import { FormControl, IconButton, InputAdornment, InputLabel, MenuItem, OutlinedInput, Select, TextField } from '@mui/material';
import Grid from '@mui/material/Grid2';
import React, { useEffect, useState } from 'react';
import api from '../../api';
import { Course } from '../../types/Course';
export interface BasicInformationStepProps {
    onChange: (key: string, value: string | number) => void;
}
export const BasicInformationStep = ({ onChange }: BasicInformationStepProps) => {
    const [showPassword, setShowPassword] = useState(false);
    const [Courses, setCourses] = useState<Course[]>([]);
    const handleClickShowPassword = () => setShowPassword((show) => !show);

    const getCourse = () => {
        api.get('/courses/all').then((response) => {
            setCourses(response.data);
            console.log(response.data);
        }
        ).catch((error) => {
            console.log(error);
        });
    }
    useEffect(getCourse, []);
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
                    onInput={(e) => {
                        console.log(e)
                        onChange('name', (e.target as HTMLInputElement).value);
                    }}
                />
            </Grid>
            <Grid size={6}>
                <TextField
                    required
                    id="rg"
                    label="RG"
                    fullWidth
                    onInput={(e) => {
                        onChange('rg', (e.target as HTMLInputElement).value);
                    }}
                />
            </Grid>
            <Grid size={6}>
                <TextField
                    required
                    id="cpf"
                    label="CPF"
                    fullWidth
                    onInput={(e) => {
                        onChange('cpf', (e.target as HTMLInputElement).value);
                    }}
                />
            </Grid>
            <Grid size={6}>
                <FormControl fullWidth>
                    <InputLabel id="course-label">Curso</InputLabel>
                    <Select
                        labelId="course-label"
                        id="course"
                        label="Curso"
                        onChange={(e) => {
                            onChange('courseId', e.target.value as string);
                        }}
                    >
                        {Courses.map((course) => (
                            <MenuItem key={course.id} value={course.id}>
                                {course.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </Grid>
            <Grid size={6}>
                <TextField
                    required
                    id="email"
                    label="Email"
                    fullWidth
                    onInput={(e) => {
                        onChange('email', (e.target as HTMLInputElement).value);
                    }}
                />
            </Grid>
            <Grid size={6}>
                <FormControl variant="outlined" fullWidth>
                    <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
                    <OutlinedInput
                        id="outlined-adornment-password"
                        type={showPassword ? 'text' : 'password'}
                        fullWidth
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
                        onInput={(e) => {
                            onChange('password', (e.target as HTMLInputElement).value);
                        }}
                    />
                </FormControl>
            </Grid>
        </Grid>

    )
}