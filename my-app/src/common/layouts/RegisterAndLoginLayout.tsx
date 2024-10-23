import { Box } from "@mui/material";

interface RegisterAndLoginLayoutProps {
    children: React.ReactNode;
}


export const RegisterAndLoginLayout: React.FC<RegisterAndLoginLayoutProps> = ({ children }) => {



    return (
        <div style={{ height: "100vh", backgroundColor: "#A9A9A9", display: "flex", textAlign: "center", justifyContent: "center", alignItems:"center"}}>
            <Box sx={{ backgroundColor: "white", border: "3px solid black", width: "90%", height: "70%", borderRadius: "16px", marginTop: "20px" }}>
                {children}
            </Box>
        </div>
    );
};