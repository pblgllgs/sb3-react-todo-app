import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import ListTodoComponent from "./components/ListTodoComponent";
import TodoComponent from "./components/TodoComponent";
import HeaderComponent from "./components/HeaderComponent.jsx";
import FooterComponent from "./components/FooterComponent.jsx";

function App() {
  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          <Route path="/add-todo" element={<TodoComponent />} />
          <Route path="/update-todo/:id" element={<TodoComponent />} />
          <Route path="/" element={<ListTodoComponent />} />
          <Route path="/todos" element={<ListTodoComponent />} />
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </>
  );
}

export default App;
