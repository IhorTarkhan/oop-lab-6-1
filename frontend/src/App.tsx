import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Nav from "./components/Nav";
import WelcomePage from "./pages/Homepage";
import SecuredPage from "./pages/Securedpage";

export const App = () => {
  return (
    <div>
      <Nav />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<WelcomePage />} />
          <Route path="/secured" element={<SecuredPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
};
