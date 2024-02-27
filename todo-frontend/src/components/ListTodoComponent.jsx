import { useEffect, useState } from "react";
import {
  completeTodo,
  deleteTodo,
  getAllTodos,
  inCompleteTodo,
} from "../services/TodoService";
import { useNavigate } from "react-router-dom";
import { Check2Circle, PatchExclamation } from "react-bootstrap-icons";
import Swal from "sweetalert2";

const ListTodoComponent = () => {
  const [todos, setTodos] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    const response = await getAllTodos();
    return setTodos(response.data);
  };

  const handleNewTodo = () => {
    navigate("/add-todo");
  };

  const handleUpdateTodo = (id) => {
    navigate(`/update-todo/${id}`);
  };

  const handleDeleteTodo = (id) => {
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!",
    }).then((result) => {
      if (result.isConfirmed) {
        deleteTodo(id)
          .then(() => fetchData())
          .catch((err) => console.log(err));
        navigate("/todos");
        Swal.fire({
          title: "Deleted!",
          text: "Your data has been deleted.",
          icon: "success",
        });
      }
    });
  };

  const handleCompleteTodo = (id) => {
    completeTodo(id)
      .then(() => fetchData())
      .catch((err) => console.log(err));
    navigate("/todos");
  };

  const handleInCompleteTodo = (id) => {
    inCompleteTodo(id)
      .then(() => fetchData())
      .catch((err) => console.log(err));
    navigate("/todos");
  };

  return (
    <>
      <div className="container">
        <h2 className="text-center">List of Todos</h2>
        <button className="btn btn-primary mb-2 mt-2" onClick={handleNewTodo}>
          Add Todo
        </button>
        <div>
          <table className="table table-bordered table-striped">
            <thead>
              <tr>
                <th>Todo Title</th>
                <th>Todo Description</th>
                <th>Todo Completed</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {todos.map((todo, i) => {
                return (
                  <tr key={i}>
                    <td>{todo.title}</td>
                    <td>{todo.description}</td>
                    <td className="text-center">
                      {todo.completed ? (
                        <Check2Circle color="green" />
                      ) : (
                        <PatchExclamation color="red" />
                      )}
                    </td>
                    <td>
                      <button
                        className="btn btn-info m-1"
                        onClick={() => handleUpdateTodo(todo.id)}
                      >
                        Update
                      </button>
                      <button
                        className="btn btn-danger m-1"
                        onClick={() => handleDeleteTodo(todo.id)}
                      >
                        Delete
                      </button>
                      <button
                        className={`btn btn-success m-1 ${
                          todo.completed ? "disabled" : ""
                        }`}
                        onClick={() => handleCompleteTodo(todo.id)}
                      >
                        Complete
                      </button>
                      <button
                        className={`btn btn-warning ${
                          todo.completed ? "" : "disabled"
                        }`}
                        onClick={() => handleInCompleteTodo(todo.id)}
                      >
                        In-Complete
                      </button>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default ListTodoComponent;
