import { useState } from "react";
import { registerUser } from "../services/AuthService";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

const RegisterComponent = () => {
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleRegistration = (e) => {
    e.preventDefault();
    const user = {
      name,
      username,
      email,
      password,
    };
    registerUser(user)
      .then((response) => {
        console.log("🚀 ~ registerUser ~ response:", response);
        Swal.fire({
          position: "top-end",
          icon: "success",
          title: "User registered successfully!!",
          showConfirmButton: false,
          timer: 1500,
        });
        setName("");
        setUsername("");
        setEmail("");
        setPassword("");
        navigate("/login");
      })
      .catch((err) => console.log(err));
  };

  return (
    <div className="container">
      <br />
      <br />
      <div className="row">
        <div className="col-md-6 offset-md-3">
          <div className="card">
            <div className="card-header">
              <h2 className="text-center">User Registration</h2>
            </div>
            <div className="card-body">
              <form>
                <div className="row mb-3">
                  <label className="col-md-3 form-label">Name:</label>
                  <div className="col-md-9">
                    <input
                      className="form-control"
                      type="text"
                      placeholder="Enter name"
                      onChange={(e) => setName(e.target.value)}
                      name="name"
                      value={name}
                    />
                  </div>
                </div>
                <div className="row mb-3">
                  <label className="col-md-3 form-label">Username:</label>
                  <div className="col-md-9">
                    <input
                      className="form-control"
                      type="text"
                      placeholder="Enter username"
                      onChange={(e) => setUsername(e.target.value)}
                      name="username"
                      value={username}
                    />
                  </div>
                </div>
                <div className="row mb-3">
                  <label className="col-md-3 form-label">Email:</label>
                  <div className="col-md-9">
                    <input
                      className="form-control"
                      type="email"
                      placeholder="Enter email"
                      onChange={(e) => setEmail(e.target.value)}
                      name="email"
                      value={email}
                    />
                  </div>
                </div>
                <div className="row mb-3">
                  <label className="col-md-3 form-label">Password:</label>
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
                    onClick={handleRegistration}
                  >
                    Save
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

export default RegisterComponent;
