import React from 'react';
import { Drawer, List, ListItem, ListItemText, Avatar, Divider, Typography, Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';

interface LeftNavigationMenuProps {
  onLogout: () => void;
  userName: string;
}

const LeftNavigationMenu: React.FC<LeftNavigationMenuProps> = ({ onLogout, userName }) => {
  const navigate = useNavigate();

  return (
    <Drawer variant="permanent" anchor="left">
      <Box sx={{ width: 250, padding: 2 }}>
        <Typography variant="h6" gutterBottom>
          Menu
        </Typography>
        <List>
          <ListItem onClick={() => navigate('/teacher/dashboard')}>
            <ListItemText primary="Dashboard" />
          </ListItem>
          <ListItem onClick={() => navigate('/teacher/extrato')}>
            <ListItemText primary="Extrato" />
          </ListItem>
          <Divider />
          <ListItem onClick={onLogout}>
            <Avatar sx={{ marginRight: 1 }} />
            <ListItemText primary={userName} secondary="Log out" />
          </ListItem>
        </List>
      </Box>
    </Drawer>
  );
};

export default LeftNavigationMenu;
