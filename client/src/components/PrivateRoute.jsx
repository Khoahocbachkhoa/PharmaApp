import { Navigate } from "react-router-dom";

const PrivateRoute = ({ children }) => {
  const loggedIn = localStorage.getItem("loggedIn");
  return (loggedIn == "true" || loggedIn == true) ? children : <Navigate to="/login" replace />;
};

export default PrivateRoute;