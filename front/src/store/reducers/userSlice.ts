// userSlice.ts

import { createSlice, PayloadAction, Store } from "@reduxjs/toolkit";
import { UserState } from "../../types";

// Function to retrieve user state from sessionStorage
const loadUserState = (): UserState => {
  const storedState = sessionStorage.getItem("userState");
  return storedState ? JSON.parse(storedState) : initialState;
};

const initialState: UserState = {
  user: {
    memberId: "",
    name: "",
    password: "",
    email: "",
    nickname: "",
  },
  isLoggedIn: false,
};

const userSlice = createSlice({
  name: "user",
  initialState: loadUserState(),
  reducers: {
    login: (
      state,
      action: PayloadAction<{ user: UserState["user"]; token: string }>
    ) => {
      const { user } = action.payload;
      state.user = user;
      state.isLoggedIn = true;
      console.log("login", state.isLoggedIn);
    },
    logout: (state) => {
      state.user = initialState.user;
      state.isLoggedIn = false;
    },
    signup: (
      state,
      action: PayloadAction<{ user: UserState["user"]; token: string }>
    ) => {
      const { user, token } = action.payload;
      state.user = user;
      state.isLoggedIn = true;
    },
  },
});

const saveUserState = (state: UserState) => {
  sessionStorage.setItem("userState", JSON.stringify(state));
};

export const setupUserStatePersistence = (store: Store) => {
  store.subscribe(() => {
    const state = store.getState().user;
    saveUserState(state);
  });
};

export const { login, logout, signup } = userSlice.actions;
export const selectUser = (state: { user: UserState }) => state.user.user;
export const selectIsLoggedIn = (state: { user: UserState }) =>
  state.user.isLoggedIn;

export default userSlice.reducer;