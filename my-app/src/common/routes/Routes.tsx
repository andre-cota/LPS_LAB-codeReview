import { createBrowserRouter, Navigate } from "react-router-dom";
import App from "../../App";
import { RegisterAndLoginLayout } from "../layouts/RegisterAndLoginLayout";
import RegisterStudent from "../pages/studentRegister";
import EnterpriseRegister from "../pages/enterpriseRegister";
import Login from "../pages/login";
import TeacherDashboard from "../pages/teacher/TeacherDashboard";
import { Typography } from "@mui/material";
import Extrato from "../pages/Extrato";
import { EnterpriseDashboard } from "../pages/enterpriseDashboard/EnterpriseDashboard";

const isAuthenticated = () => {
  const user = JSON.parse(localStorage.getItem('user') || '{}');
  return user?.accessToken ? true : false;
};

const SystemRoutes = createBrowserRouter([
  {
    path: "/",
    element: <Navigate to="/login" />
  },
  {
    path: '/login',
    element: <Login />
  },
  {
    path: '/register',
    element: <RegisterAndLoginLayout />,
    children: [
      {
        path: 'student',
        element: <RegisterStudent />
      },
      {
        path: 'enterprise',
        element: <EnterpriseRegister />
      }
    ]
  },
  {
    path: '/teacher/dashboard',
    element: <TeacherDashboard />
  },
  {
    path: '/teacher/extrato',
    element: <Extrato />
  },
  {
    path: '/student/extrato',
    element: <Extrato />
  },
  {
    path: '/enterprise/dashboard',
    element: <EnterpriseDashboard />
  }
]);

export default SystemRoutes;