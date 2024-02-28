import { NavLink, useNavigate } from "react-router-dom";
import { isUserLoggedIn, logout } from "../services/AuthService";
import Swal from "sweetalert2";

const HeaderComponent = () => {
  const navigate = useNavigate();
  const isAuth = isUserLoggedIn();
  console.log("🚀 ~ HeaderComponent ~ isAuth:", isAuth);

  const handleLogout = () => {
    Swal.fire({
      title: "Terminate session?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, terminate it!",
    }).then((result) => {
      if (result.isConfirmed) {
        logout();
        navigate("/login");
        Swal.fire({
          title: "Session terminated!",
          text: "Your session has been terminated.",
          icon: "success",
        });
      }
    });
  };

  return (
    <div>
      <header>
        <nav className="navbar navbar-expand-md navbar-dark bg-dark">
          <div>
            <a href="http://localhost:3000" className="navbar-brand">
              Todo Management Application
            </a>
          </div>
          <div className="collapse navbar-collapse">
            <ul className="navbar-nav">
              {isAuth && (
                <li className="nav-item">
                  <NavLink to="/todos" className="nav-link">
                    Todos
                  </NavLink>
                </li>
              )}
            </ul>
          </div>

          <ul className="navbar-nav">
            {!isAuth && (
              <>
                <li className="nav-item">
                  <NavLink to={"/register"} className="nav-link">
                    Register
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to={"/login"} className="nav-link">
                    Login
                  </NavLink>
                </li>
              </>
            )}

            {isAuth && (
              <li className="nav-item">
                <NavLink
                  className="nav-link"
                  onClick={handleLogout}
                >
                  Logout
                </NavLink>
              </li>
            )}
          </ul>
        </nav>
      </header>
    </div>
  );
};

export default HeaderComponent;
