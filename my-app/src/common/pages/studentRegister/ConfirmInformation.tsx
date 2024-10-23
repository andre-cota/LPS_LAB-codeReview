import { Box, Typography } from '@mui/material';
import React from 'react';
import { Student } from '../../types/student';

type ConfirmInformationProps = {
    student: Partial<Student>;
};

export const ConfirmInformation: React.FC<ConfirmInformationProps> = ({ student }) => {


    return (
        <Box sx={{ padding: 2 }}>
            <Typography variant="h4" gutterBottom>
                Confirme seus dados:
            </Typography>
            <Typography variant="body1">
                <strong>Name:</strong> {student.name}
            </Typography>
            <Typography variant="body1">
                <strong>Address:</strong> {student.address?.street}, {student.address?.city}, {student.address?.state}, {student.address?.zipCode}
            </Typography>
            <Typography variant="body1">
                <strong>RG:</strong> {student.rg}
            </Typography>
            <Typography variant="body1">
                <strong>CPF:</strong> {student.cpf}
            </Typography>
            <Typography variant="body1">
                <strong>Balance:</strong> {student.balance}
            </Typography>
            <Typography variant="body1">
                <strong>Course:</strong> {student.courseId?.toString() ?? 'N/A'}
            </Typography>
        </Box>
    );
};