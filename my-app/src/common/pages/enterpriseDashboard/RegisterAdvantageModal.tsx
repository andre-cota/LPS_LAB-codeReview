import React, { useState, useEffect } from 'react';
import {
    Box,
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    TextField,
} from '@mui/material';
import { useNotification } from '../../hooks/useNotification';
import api from '../../api';
import { Advantage } from '../../types/Advantage';

interface RegisterAdvantageModalProps {
    open: boolean;
    onClose: () => void;
    advantage?: Advantage;
    setReload: React.Dispatch<React.SetStateAction<boolean>>;
}

export const RegisterAdvantageModal: React.FC<RegisterAdvantageModalProps> = ({
    open,
    onClose,
    advantage,
    setReload,
}) => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [value, setValue] = useState<number | ''>('');
    const [urlImage, setUrlImage] = useState('');
    const { showNotification } = useNotification();

    useEffect(() => {
        if (advantage) {
            setName(advantage.name || '');
            setDescription(advantage.description || '');
            setValue(advantage.advantageValue || '');
            setUrlImage(advantage.urlImage || '');
        } else {
            resetForm();
        }
    }, [advantage]);

    const resetForm = () => {
        setName('');
        setDescription('');
        setValue('');
        setUrlImage('');
    };

    const handleSubmit = () => {
        const storedUserId = localStorage.getItem('userId');
        const companyId = storedUserId ? JSON.parse(storedUserId).id : null;
        const payload = { name, description, value, urlImage, companyId };
        console.log(payload)

        const apiCall = advantage
            ? api.put(`/advantages/${advantage.id}`, JSON.stringify(payload))
            : api.post('/advantages', JSON.stringify(payload));

        apiCall
            .then((response) => {
                showNotification({
                    message: response.data.message || 'Advantage saved successfully!',
                    type: 'success',
                });
                setReload(true);
                onClose();
            })
            .catch((error) => {
                showNotification({
                    message: error.response?.data?.message || 'Failed to save advantage.',
                    type: 'error',
                });
            });
    };

    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
            <DialogTitle>
                {advantage ? 'Edit Advantage' : 'Register Advantage'}
            </DialogTitle>
            <DialogContent>
                <Box display="flex" flexDirection="column" gap={2}>
                    <TextField
                        label="Advantage Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        fullWidth
                        required
                    />
                    <TextField
                        label="Description"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        fullWidth
                        multiline
                        rows={4}
                        required
                    />
                    <TextField
                        label="Value"
                        value={value}
                        onChange={(e) => {
                            const input = e.target.value;
                            const sanitizedInput = input.replace(/[^0-9]/g, '').replace(/^0+(?=\d)/, '');
                            setValue(sanitizedInput ? Number(sanitizedInput) : '');
                        }}
                        fullWidth
                        required
                    />
                    <TextField
                        label="Image URL"
                        value={urlImage}
                        onChange={(e) => setUrlImage(e.target.value)}
                        fullWidth
                    />
                </Box>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose} color="secondary">
                    Cancel
                </Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">
                    Save
                </Button>
            </DialogActions>
        </Dialog>
    );
};
