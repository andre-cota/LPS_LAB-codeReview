import { Box, IconButton } from "@mui/material";
import { useNavigate } from "react-router"
import KeyboardReturnIcon from '@mui/icons-material/KeyboardReturn';

type GoBackIconProps = {
    link: string;
}

const GoBackIcon: React.FC<GoBackIconProps> = ({link}) => {
    const navigate = useNavigate()

    const onClick = () => {
        navigate(link)
    } 

    return (
        <Box sx={{ display: "flex" }}>
            <IconButton onClick={onClick}>
                <KeyboardReturnIcon />
            </IconButton>
        </Box>
    )
}

export default GoBackIcon