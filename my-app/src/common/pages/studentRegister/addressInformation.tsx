import { TextField } from '@mui/material';
import Grid from '@mui/material/Grid2';
import { useEffect, useState } from "react";
import api from "../../api/cepApi";
import { Address } from '../../types/address';
export interface AddressInformationStepProps {
    onChange: (key: string, value: string | number) => void;
    address: Partial<Address>
}

export const AddressInformationStep = ({ onChange, address }: AddressInformationStepProps) => {
    const [cep, setCep] = useState('');

    const autoComplete = () => {
        api.get(`${cep}`).then((response) => {
            const { logradouro, bairro, localidade, uf, cep } = response.data
            onChange('street', logradouro)
            onChange('neighborhood', bairro)
            onChange('city', localidade)
            onChange('state', uf)
            onChange('zipCode', cep)

        }).catch((error) => {
            console.log(error);
        });
    }
    useEffect(autoComplete, [cep])

    return (<Grid container spacing={2} marginTop={5}>
        <Grid size={4}  >
            <TextField
                required
                id="zipCode"
                label="cep"
                fullWidth
                value={address.zipCode}
                onChange={(e) => {
                    setCep(e.target.value)
                    onChange('zipCode', e.target.value);
                }}
            />
        </Grid>
        <Grid size={8}>

        </Grid>
        <Grid size={6}>
            <TextField
                required
                id="city"
                label="Cidade"
                fullWidth
                value={address.city || ''}
                onChange={(e) => {
                    onChange('city', e.target.value);
                }}
            />
        </Grid>
        <Grid size={4}>
            <TextField
                required
                id="neighborhood"
                label="Bairro"
                fullWidth
                value={address.neighborhood || ''}
                onChange={(e) => {
                    onChange('neighborhood', e.target.value);
                }}
            />
        </Grid>
        <Grid size={2}>
            <TextField
                required
                id="state"
                label="Estado"
                fullWidth
                value={address.state || ''}
                onChange={(e) => {
                    onChange('state', e.target.value);
                }}
            />
        </Grid>
        <Grid size={6}>
            <TextField
                required
                id="street"
                label="Rua"
                fullWidth
                value={address.street || ''}
                onChange={(e) => {
                    onChange('street', e.target.value);
                }}
            />
        </Grid>
        <Grid size={4}>
            <TextField

                id="complement"
                label="Complemento"
                fullWidth
                value={address.complement}
                onChange={(e) => {
                    onChange('complement', e.target.value);
                }}
            />
        </Grid>
        <Grid size={2}>
            <TextField
                required
                id="number"
                label="Numero"
                fullWidth
                value={address.number}
                onChange={(e) => {
                    onChange('number', e.target.value);
                }}
            />
        </Grid>
    </Grid>
    )
}