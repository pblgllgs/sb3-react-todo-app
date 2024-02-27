import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { createTodo, getTodo, updateTodo } from "../services/TodoService";
import Swal from "sweetalert2";

const TodoComponent = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [completed, setCompleted] = useState(false);
  const navigate = useNavigate();
  const { id } = useParams();

  const saveTodo = (e) => {
    e.preventDefault();
    const todo = {
      title,
      description,
      completed,
    };
    try {
      if (id) {
        updateTodo(id, todo);
        Swal.fire({
          position: "top-end",
          icon: "success",
          title: "Todo updated successfully!!",
          showConfirmButton: false,
          timer: 1500,
        });
        setTitle("");
        setDescription("");
        setCompleted(false);
        navigate("/todos");
      } else {
        createTodo(todo);
        Swal.fire({
          position: "top-end",
          icon: "success",
          title: "Todo created successfully!!",
          showConfirmButton: false,
          timer: 1500,
        });
        setTitle("");
        setDescription("");
        setCompleted(false);
        navigate("/todos");
      }
    } catch (error) {
        console.log(error);
        Swal.fire({
          position: "top-end",
          icon: "error",
          title: "Error then try save the todo, see logs",
          showConfirmButton: false,
          timer: 1500,
        });
    }
  };

  const isActive= () => {
    return id>0;
  }

  const pageTitle = () => {
    if (id) {
      return <h2 className="text-center">Update Todo</h2>;
    } else {
      return <h2 className="text-center">Add Todo</h2>;
    }
  };

  useEffect(() => {
    if (id) {
      const fetchDataAndSetTodoDetails = async (id) => {
        const response = await getTodo(id);
        setTitle(response.data.title);
        setDescription(response.data.description);
        setCompleted(response.data.completed);
      };
      fetchDataAndSetTodoDetails(id);
    }
  }, [id]);

  return (
    <>
      <div className="container">
        <br />
        <div className="row">
          <div className="card col-md-6 offset-md-3 offset-md-3">
            {pageTitle()}
            <div className="card-body">
              <form>
                <div className="form-group mb-2">
                  <label className="form-label">Todo Title:</label>
                  <input
                    className="form-control"
                    type="text"
                    placeholder="Enter title"
                    onChange={(e) => setTitle(e.target.value)}
                    name="title"
                    value={title}
                  />
                </div>
                <div className="form-group mb-2">
                  <label className="form-label">Todo Description:</label>
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Enter description"
                    onChange={(e) => setDescription(e.target.value)}
                    name="description"
                    value={description}
                  />
                </div>
                <div className="form-check form-switch">
                  <input
                    className="form-check-input"
                    type="checkbox"
                    id="flexSwitchCheckDefault"
                    name="completed"
                    value={completed}
                    checked={completed}
                    disabled={!isActive()}
                    onChange={() => setCompleted(!completed)}
                  />
                  <label
                    className="form-check-label"
                    htmlFor="flexSwitchCheckDefault"
                  >
                    Completed
                  </label>
                </div>
                <button
                  className="btn btn-success"
                  onClick={saveTodo}
                  type="submit"
                >
                  Save
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default TodoComponent;
