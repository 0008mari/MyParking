import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import LoginPage from "./pages/Login/";
import Register from "./pages/Register";

function App() {
  return (
    <Router basename={process.env.PUBLIC_URL}>
      <Routes>
        <Route path={"/login"} element={<LoginPage />}></Route>
        <Route path={"/register"} element={<Register />}></Route>
      </Routes>
    </Router>
  );
}

export default App;