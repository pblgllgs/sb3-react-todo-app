import { useState } from "react";
import {
  loginUser,
  saveLoggedInUser,
  storeToken,
} from "../services/AuthService";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

const LoginComponent = () => {
  const [usernameOrEmail, setUsernameOrEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    const user = {
      usernameOrEmail,
      password,
    };
    console.log("🚀 ~ handleRegistration ~ user:", user);
    await loginUser(user)
      .then((response) => {
        console.log("🚀 ~ .then ~ response:", response.data);
        const token = `${response.data.tokenType} ${response.data.accessToken}`;
        storeToken(token);
        saveLoggedInUser(usernameOrEmail, response.data.role);
        console.log("🚀 ~ .then ~ response.data.role:", response.data.role);
        Swal.fire({
          position: "top-end",
          icon: "success",
          title: "User registered successfully!!",
          showConfirmButton: false,
          timer: 1500,
        });
      })
      .catch((err) => console.log(err));
    setUsernameOrEmail("");
    setPassword("");
    navigate("/todos");
  };

  return (
    <div className="container">
      <br />
      <br />
      <div className="row">
        <div className="col-md-6 offset-md-3">
          <div className="card">
            <div className="card-header">
              <h2 className="text-center">User Login</h2>
            </div>
            <div className="card-body">
              <form>
                <div className="row mb-3">
                  <label className="col-md-3 form-label">
                    Username or Email:
                  </label>
                  <div className="col-md-9">
                    <input
                      className="form-control"
                      type="text"
                      placeholder="Enter username or email"
                      onChange={(e) => setUsernameOrEmail(e.target.value)}
                      name="usernameOrEmail"
                      value={usernameOrEmail}
                    />
                  </div>
                </div>
                <div className="row mb-3">
                  <label className="col-md-3 form-label">password:</label>
                  <div className="col-md-9">
                    <input
                      className="form-control"
                      type="password"
                      placeholder="Enter password"
                      onChange={(e) => setPassword(e.target.value)}
                      name="password"
                      value={password}
                    />
                  </div>
                </div>
                <div className="form-group mb-3">
                  <button
                    type="submit"
                    className="btn btn-primary"
                    onClick={handleLogin}
                  >
                    Sign in
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginComponent;
