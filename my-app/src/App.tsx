import { useEffect } from 'react';
import './styles/global.css';
import { useLocation, useNavigate } from 'react-router';

function App() {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
      if (location.pathname === "/") {
          navigate("/login");
      }
  }, [navigate, location.pathname]);

  return (
    <></>
  );
}

export default App;
