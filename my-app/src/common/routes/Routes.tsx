import { createBrowserRouter } from "react-router-dom";
import App from "../../App";
import { RegisterAndLoginLayout } from "../layouts/RegisterAndLoginLayout";
import RegisterStudent from "../pages/studentRegister";


const SystemRoutes = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [

    ],
  },
  {
    path: '/login',
  },

  {
    path: '/register',
    element: <RegisterAndLoginLayout>
      <RegisterStudent />
    </RegisterAndLoginLayout>

  }
]);

export default SystemRoutes;