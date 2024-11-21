import React, { useEffect, useState } from 'react';
import { Container, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, CircularProgress, Alert, TextField, TableSortLabel } from '@mui/material';
import api from '../api';
import LeftNavigationMenu from '../components/teacherLeftNavMenu/LeftNavigationMenu';
import { useLocation, useNavigate } from 'react-router';

interface Institution {
    id: number;
    name: string;
}

interface Department {
    id: number;
    name: string;
    institution: Institution;
}

interface Professor {
    id: number;
    name: string;
    email: string;
    cpf: string;
    balance: number;
    department: Department;
}

interface Address {
    id: number;
    street: string;
    number: number;
    complement: string;
    neighborhood: string;
    city: string;
    state: string;
    zipCode: string;
}

interface Course {
    id: number;
    name: string;
    department: Department;
}

interface Student {
    id: number;
    name: string;
    email: string;
    cpf: string;
    balance: number;
    rg: string;
    address: Address;
    course: Course;
}

interface DonationRequest {
    id: number;
    donationValue: number;
    professor: Professor;
    student: Student;
    date: number[];
}

interface Donation {
    id: number;
    donationValue: number;
    professor: Professor;
    student: Student;
    date: Date;
}

const Extrato: React.FC = () => {
    const [donations, setDonations] = useState<Donation[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>();
    const [searchTerm, setSearchTerm] = useState('');
    const [orderBy, setOrderBy] = useState<'date' | 'donationValue'>('date');
    const [orderDirection, setOrderDirection] = useState<'asc' | 'desc'>('asc');
    const [isStudent, setIsStudent] = useState(false);

    const [user, setUser] = useState<Professor>({} as Professor);

    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        const userIdObject = JSON.parse(localStorage.getItem('userId') || '0');
        const id = userIdObject.id;

        const isTeacherExtrato = location.pathname === '/teacher/extrato';
        const endpoint = isTeacherExtrato ? `/donations/professor/${id}` : `/donations/student/${id}`;

        const userEndpoint = isTeacherExtrato ? `/professors/${id}` : `/students/${id}`;
        setIsStudent(!isTeacherExtrato);

        api.get(userEndpoint)
            .then((response) => {
                setUser(response.data);
            })
            .catch(() => {
                setError('Failed to load user');
            });

        setLoading(true);
        api.get(endpoint)
            .then((response) => {
                const dados: DonationRequest[] = response.data;
                const formattedData = dados.map(item => ({
                    ...item,
                    date: new Date(item.date[0], item.date[1] - 1, item.date[2],
                        item.date[3], item.date[4], item.date[5])
                }));
                setDonations(formattedData);
                setLoading(false);
            })
            .catch(() => {
                setError('Failed to load donations');
                setLoading(false);
            });
    }, [location.pathname]);

    const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSearchTerm(event.target.value);
    };

    const handleSortRequest = (property: 'date' | 'donationValue') => {
        const isAsc = orderBy === property && orderDirection === 'asc';
        setOrderDirection(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };

    const filteredDonations = donations.filter(donation =>
        donation.student.name.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const sortedDonations = [...filteredDonations].sort((a, b) => {
        if (orderBy === 'date') {
            return (new Date(a.date).getTime() - new Date(b.date).getTime()) * (orderDirection === 'asc' ? 1 : -1);
        }
        return (a.donationValue - b.donationValue) * (orderDirection === 'asc' ? 1 : -1);
    });

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;

    const handleLogout = () => {
        localStorage.clear();
        navigate('/login');
    }
    return (
        <Container>
            <LeftNavigationMenu onLogout={handleLogout} userName={user.name || 'User'} />
            <Typography variant="h4" gutterBottom>Extrato</Typography>
            <TextField
                label={isStudent ? "Search by Professor Name" : "Search by Student Name"}
                variant="outlined"
                fullWidth
                margin="normal"
                value={searchTerm}
                onChange={handleSearchChange}
            />
            <TableContainer>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>{isStudent ? "Professor Name" : "Student Name"}</TableCell>
                            {!isStudent && <TableCell>Course Name</TableCell>}
                            <TableCell sortDirection={orderBy === 'date' ? orderDirection : false}>
                                <TableSortLabel
                                    active={orderBy === 'date'}
                                    direction={orderBy === 'date' ? orderDirection : 'asc'}
                                    onClick={() => handleSortRequest('date')}
                                >
                                    Date
                                </TableSortLabel>
                            </TableCell>
                            <TableCell sortDirection={orderBy === 'donationValue' ? orderDirection : false}>
                                <TableSortLabel
                                    active={orderBy === 'donationValue'}
                                    direction={orderBy === 'donationValue' ? orderDirection : 'asc'}
                                    onClick={() => handleSortRequest('donationValue')}
                                >
                                    Amount
                                </TableSortLabel>
                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {sortedDonations.map((donation) => (
                            <TableRow key={donation.id}>
                                <TableCell>{isStudent ? donation.professor.name : donation.student.name}</TableCell>
                                {!isStudent && <TableCell>{donation.student.course.name}</TableCell>}
                                <TableCell>{new Date(donation.date).toLocaleDateString()}</TableCell>
                                <TableCell>{donation.donationValue}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Container>
    );
};

export default Extrato;