import React, { useState } from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Typography,
    Divider,
    TextField,
    Button,
    Box
} from '@mui/material';
import { Student } from '../../types/student';
import { Advantage } from '../../types/Advantage';
import api from '../../api';


interface TradeAdvantageModalProps {
    advantage: Advantage;
    student: Student;
    setStudent: React.Dispatch<React.SetStateAction<Student>>;
    onClose: () => void;
}

const TradeAdvantageModal: React.FC<TradeAdvantageModalProps> = ({ advantage, student, setStudent, onClose }) => {
    const [quantity, setQuantity] = useState(1);
    const totalPrice = advantage.advantageValue * quantity;

    const handleTrade = async () => {
        try {
            const response = await api.post('/purchases', {
                studentId: student.id,
                price: totalPrice,
                quantity,
                advantageId: advantage.id
            });

            if (response.data) {
                setStudent((prev) => ({ ...prev, balance: prev.balance - totalPrice }));
                onClose();
            }
        } catch (error) {
            console.error('Error trading advantage:', error);
        }
    };

    return (
        <Dialog open onClose={onClose} fullWidth>
            <DialogTitle>Trade Advantage</DialogTitle>
            <DialogContent>
                <Box sx={{ textAlign: 'center' }}>
                    <img
                        src={advantage.urlImage}
                        alt={advantage.name}
                        style={{ maxWidth: '100%', maxHeight: '200px' }}
                    />
                    <Typography variant="h6" sx={{ mt: 2 }}>
                        {advantage.name}
                    </Typography>
                    <Typography variant="body1" sx={{ mt: 1 }}>
                        {advantage.description}
                    </Typography>
                </Box>
                <Divider sx={{ my: 2 }} />
                <Typography variant="h5" align="center">
                    Price: {advantage.advantageValue}
                </Typography>
                <TextField
                    type="number"
                    label="Quantity"
                    value={quantity}
                    onChange={(e) => setQuantity(Number(e.target.value))}
                    fullWidth
                    sx={{ mt: 2 }}
                    inputProps={{ min: 1 }}
                />
                <Typography
                    variant="h6"
                    align="center"
                    sx={{
                        mt: 2,
                        color: totalPrice > student.balance ? 'red' : 'inherit',
                    }}
                >
                    Total Price: {totalPrice}
                </Typography>
            </DialogContent>
            <DialogActions sx={{ justifyContent: 'center' }}>
                <Button
                    variant="contained"
                    disabled={totalPrice > student.balance}
                    onClick={handleTrade}
                >
                    Trade
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default TradeAdvantageModal;
