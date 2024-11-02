import { createBrowserRouter, Navigate } from "react-router-dom";
import App from "../../App";
import { RegisterAndLoginLayout } from "../layouts/RegisterAndLoginLayout";
import RegisterStudent from "../pages/studentRegister";
import EnterpriseRegister from "../pages/enterpriseRegister";
import Login from "../pages/login";

const isAuthenticated = () => {
  const user = JSON.parse(localStorage.getItem('user') || '{}');
  return user?.accessToken ? true : false;
};

const SystemRoutes = createBrowserRouter([
  {
    path: "/",
    element: isAuthenticated() ? <App /> : <Navigate to="/login" />
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
  }
]);

export default SystemRoutes;