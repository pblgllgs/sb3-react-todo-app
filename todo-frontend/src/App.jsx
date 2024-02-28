import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import ListTodoComponent from "./components/ListTodoComponent";
import TodoComponent from "./components/TodoComponent";
import HeaderComponent from "./components/HeaderComponent.jsx";
import FooterComponent from "./components/FooterComponent.jsx";
import RegisterComponent from "./components/RegisterComponent.jsx";
import LoginComponent from "./components/LoginComponent.jsx";
import AuthenticateRoute from "./components/AuthenticateRouteComponent.jsx";

function App() {

  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          <Route path="/" element={<LoginComponent />} />
          <Route path="/login" element={<LoginComponent />} />
          <Route path="/register" element={<RegisterComponent />} />
          <Route
            path="/add-todo"
            element={
              <AuthenticateRoute>
                <TodoComponent />
              </AuthenticateRoute>
            }
          />
          <Route
            path="/update-todo/:id"
            element={
              <AuthenticateRoute>
                <TodoComponent />
              </AuthenticateRoute>
            }
          />
          <Route
            path="/todos"
            element={
              <AuthenticateRoute>
                <ListTodoComponent />
              </AuthenticateRoute>
            }
          />
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </>
  );
}

export default App;
