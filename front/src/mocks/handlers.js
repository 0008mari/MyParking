import { rest } from "msw";
export const handlers = [
  rest.post("/oauth2/authorization/google", (req, res, ctx) => {
    return res(ctx.status(200));
  }),

  rest.get("/parkings/all", (req, res, ctx) => {
    return res(ctx.status(200), ctx.json());
  }),
];
