import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Main from "./pages/Main";
import LoginPage from "./pages/Login/";
import Register from "./pages/Register";
import ReviewPage from "./pages/Review";

function App() {
  return (
    <Router basename={process.env.PUBLIC_URL}>
      <Routes>
        <Route path={"/"} element={<Main />}></Route>
        <Route path={"/login"} element={<LoginPage />}></Route>
        <Route path={"/register"} element={<Register />}></Route>
        <Route path={"/review"} element={<ReviewPage />}></Route>
      </Routes>
    </Router>
  );
}

export default App;
