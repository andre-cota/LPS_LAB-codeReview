import React, { useState } from 'react';
import { Modal, Box, Typography, Button, TextField } from '@mui/material';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import api from '../../api';
import { Professor } from '../../types/Professor';

interface DonateModalProps {
  open: boolean;
  onClose: () => void;
  professorId: number;
  studentId: number;
  onDonationSuccess: (data: Professor) => void;
}

const DonateModal: React.FC<DonateModalProps> = ({ open, onClose, professorId, studentId, onDonationSuccess }) => {
  const [amount, setAmount] = useState<number | ''>('');

  const handleAmountChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = parseInt(e.target.value, 10);
    setAmount(value >= 0 ? value : '');
  };

  const handleConfirm = async () => {
    if (amount && amount > 0) {
        let requestBody = {
            professorId: professorId,
            studentId: studentId,
            amount: amount
        }
      try {
        await api.post('/donations', requestBody).then((response) => {
            onDonationSuccess(response.data.professor)
            });
        toast.success('Donation successful');
        onClose();
      } catch (error) {
        toast.error('An error occurred while sending coins');
      }
    } else {
      toast.warn('Please enter a valid amount');
    }
  };

  return (
    <>
      <Modal open={open} onClose={onClose} aria-labelledby="donation-modal-title">
        <Box
          sx={{
            position: 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            width: 400,
            bgcolor: 'background.paper',
            border: '2px solid #000',
            boxShadow: 24,
            p: 4,
          }}
        >
          <Typography id="donation-modal-title" variant="h6" component="h2">
            Quantas moedas quer doar?
          </Typography>
          <TextField
            type="number"
            value={amount}
            onChange={handleAmountChange}
            fullWidth
            margin="normal"
            inputProps={{ min: 0 }}
          />
          <Box sx={{ display: 'flex', justifyContent: 'flex-end', gap: 1 }}>
            <Button variant="outlined" onClick={onClose}>
              Cancelar
            </Button>
            <Button variant="contained" onClick={handleConfirm}>
              Confirmar
            </Button>
          </Box>
        </Box>
      </Modal>
      <ToastContainer />
    </>
  );
};

export default DonateModal;
