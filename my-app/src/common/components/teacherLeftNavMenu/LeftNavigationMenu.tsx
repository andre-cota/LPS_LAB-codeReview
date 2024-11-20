import React, { useEffect, useState } from 'react';
import { Drawer, List, ListItem, ListItemText, Avatar, Divider, Typography, Box } from '@mui/material';
import { useLocation, useNavigate } from 'react-router-dom';

interface LeftNavigationMenuProps {
  onLogout: () => void;
  userName: string;
}

const LeftNavigationMenu: React.FC<LeftNavigationMenuProps> = ({ onLogout, userName }) => {
  const [dashboardRoute, setDashboardRoute] = useState("/");
  const [extratoRoute, setExtratoRoute] = useState("/");
  const [isCompany, setIsCompany] = useState(false);

  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    if (location.pathname.startsWith("/teacher")) {
      setDashboardRoute("/teacher/dashboard");
      setExtratoRoute("/teacher/extrato");
    } else if (location.pathname.startsWith("/student")) {
      setDashboardRoute("/student/dashboard");
      setExtratoRoute("/student/extrato");
    } else {
      setIsCompany(true);
    }
  }, [location.pathname])

  return (
    <Drawer variant="permanent" anchor="left">
      <Box sx={{ width: 250, padding: 2 }}>
        <Typography variant="h6" gutterBottom>
          Menu
        </Typography>
        <List>
          {!isCompany && (
            <>
              <ListItem onClick={() => navigate(dashboardRoute)}>
                <ListItemText primary="Dashboard" />
              </ListItem>
              <ListItem onClick={() => navigate(extratoRoute)}>
                <ListItemText primary="Extrato" />
              </ListItem>
            </>
          )}

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
