
import { Navigate } from "react-router-dom";
import PropTypes from 'prop-types';
import { isUserLoggedIn } from "../services/AuthService.js";

const AuthenticateRoute = ({ children }) => {
    const isAuth = isUserLoggedIn();
    if (isAuth) {
      return children;
    }
    return <Navigate to={"/"} />;
  };

  AuthenticateRoute.propTypes = {
    children: PropTypes.node.isRequired,
  };

  export default AuthenticateRoute;