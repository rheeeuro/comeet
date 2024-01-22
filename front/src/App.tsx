import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import tw from "tailwind-styled-components";
import { TailwindComponent } from "tailwind-styled-components/dist/tailwind";
import { NavBar } from "./components/NavBar";
import { RoomList } from "./pages/RoomList";
import { Mainpage } from "./pages/Mainpage";
import { Community } from "./pages/Community";
import { Mypage } from "./pages/Mypage";
import { LoginPage } from "./pages/LoginPage";
import { SignupPage } from "./pages/SignupPage";
import { Room } from "./pages/Room";
import RoomChannel from "./components/RoomChannel";
import NewRoom from "./pages/NewRoom";
import ConditionCheck from "./pages/ConditionCheck";

function App() {
  //임시
  const isLogin = true;
  const [isModal, setIsModal] = React.useState<boolean>(false);

  const modalHandler = () => {
    setIsModal(!isModal);
  };

  return (
    <div className="App">
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route path="/" element={<Mainpage />}></Route>
          {isLogin ? (
            <>
              <Route path="/roomlist" element={<RoomList />}></Route>

              <Route path="/community" element={<Community />}></Route>

              <Route path="/mypage" element={<Mypage />}></Route>

              <Route path="/room/:roomId/*" element={<Room />}></Route>
            </>
          ) : (
            <>
              <Route path="/login" element={<LoginPage />}></Route>
              <Route path="/signup" element={<SignupPage />}></Route>
            </>
          )}
          <Route path="/new-room" element={<NewRoom />}></Route>
          <Route path="/before-entrance" element={<ConditionCheck />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
