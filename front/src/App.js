import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Main from "./pages/Main";
import LoginPage from "./pages/Login/";
import Register from "./pages/Register";
import ReviewPage from "./pages/Review";
import ReviewNewPage from "./pages/NewReview";

function App() {
  return (
    <Router basename={process.env.PUBLIC_URL}>
      <Routes>
        <Route path={"/"} element={<Main />} />
        <Route path={"/login"} element={<LoginPage />} />
        <Route path={"/register"} element={<Register />} />
        <Route path={"/review/:parkingId"} element={<ReviewPage />} />
        <Route path={"/review/:parkingId/new"} element={<ReviewNewPage />} />
      </Routes>
    </Router>
  );
}

export default App;
