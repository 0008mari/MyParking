import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Main from "./pages/Main";
import LoginPage from "./pages/Login/";
import Register from "./pages/Register";
import ReviewPage from "./pages/Review";
import ReviewNewPage from "./pages/NewReview";

import { Navigate, Outlet } from "react-router-dom";
import Mypage from "./pages/Mypage";

const RequireLogin = () => {
  const isLoggedIn = localStorage.getItem("userId");

  if (!isLoggedIn) {
    return <Navigate to="/login" replace />;
  }

  return (
    <>
      <Outlet />
    </>
  );
};

const RequireLogout = () => {
  const isLoggedIn = localStorage.getItem("userId");

  if (isLoggedIn) {
    return <Navigate to="/" replace />;
  }

  return (
    <>
      <Outlet />
    </>
  );
};

function App() {
  return (
    <Router basename={process.env.PUBLIC_URL}>
      <Routes>
        <Route element={<RequireLogin />}>
          <Route path={"/"} element={<Main />} />
          <Route path={"/review/:parkingId"} element={<ReviewPage />} />
          <Route path={"/review/:parkingId/new"} element={<ReviewNewPage />} />
          <Route path={"/mypage"} element={<Mypage />} />
        </Route>
        <Route element={<RequireLogout />}>
          <Route path={"/login"} element={<LoginPage />} />
          <Route path={"/register"} element={<Register />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
