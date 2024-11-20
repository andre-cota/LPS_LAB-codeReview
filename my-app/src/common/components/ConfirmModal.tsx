
import { faCircleXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    IconButton,
} from "@mui/material";

export interface ConfirmModalProps {
    openModal: boolean;
    title: string;
    dialogText?: string;
    closeAction?: () => void;
    confirmAction?: () => void;
}

export const ConfirmModal = ({ openModal, title, closeAction, confirmAction, dialogText }: ConfirmModalProps) => {
    const confirmAllAction = () => {
        closeAction!();
        confirmAction!();
    }
return (
<Dialog fullWidth maxWidth="sm" open={openModal} onClose={closeAction}> 
            <DialogTitle sx={{ backgroundColor: "#E9E6E6", display: "flex", justifyContent: "space-between" }}>{title} <IconButton onClick={closeAction}><FontAwesomeIcon icon={faCircleXmark} style={{ color: "#0C5C1B", }} /></IconButton></DialogTitle>
            <DialogContent>
                <DialogContentText>
                {dialogText}
                </DialogContentText>
            </DialogContent>
            <DialogActions>
            <Button color="error" variant="outlined" onClick={closeAction}>Cancelar</Button>
            <Button color="error" onClick={confirmAllAction}>Confirmar</Button>
            </DialogActions>
        </Dialog>
    );
}