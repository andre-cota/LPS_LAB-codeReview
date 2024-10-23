import { Box } from "@mui/material";
import { useEffect } from "react";
import { Outlet, useLocation, useNavigate } from "react-router";

export const RegisterAndLoginLayout = () => {
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        if (location.pathname === "/register") {
            navigate("/register/student");
        }
    }, [navigate, location.pathname]);

    return (
        <div style={{ height: "100vh", backgroundColor: "#A9A9A9", display: "flex", textAlign: "center", justifyContent: "center", alignItems:"center"}}>
            <Box sx={{ backgroundColor: "white", border: "3px solid black", width: "90%", height: "70%", borderRadius: "16px", marginTop: "20px" }}>
                <Outlet />
            </Box>
        </div>
    );
};