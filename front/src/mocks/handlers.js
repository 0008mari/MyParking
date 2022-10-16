import { rest } from "msw";
export const handlers = [
  // main
  rest.post("/oauth2/authorization/google", (req, res, ctx) => {
    return res(ctx.status(200));
  }),

  rest.get("/parkings/all", (req, res, ctx) => {
    return res(ctx.status(200), ctx.json());
  }),

  // review
  // reviewId로 조회
  rest.get("/reviews/:reviewId", (req, res, ctx) => {
    // parkingId로 조회, userId로 조회
    const parkingId = req.url.searchParams.get("parkingId");
    const userId = req.url.searchParams.get("userId");

    const result = {
      userId: 4,
      parkingId: 5,
      evalSpace: "LARGE",
      evalParkinglevel: "EASY",
      evalCostefficient: "CHEAP",
      evalStaff: "FRIENDLY",
      evalRevisit: "YES",
      starScore: 2,
    };

    return res(ctx.status(200), ctx.json(result));
  }),

  // 리뷰 생성
  rest.post("/reviews/new", (req, res, ctx) => {
    const result = {
      reviewId: 5,
    };
    return res(ctx.status(200), ctx.json(result));
  }),

  // 리뷰 수정
  rest.post("/reviews/:reviewId", (req, res, ctx) => {
    const result = {
      reviewId: 5,
    };
    return res(ctx.status(200), ctx.json(result));
  }),

  // 리뷰 삭제
  rest.delete("/reviews/:reviewId", (req, res, ctx) => {
    return res(ctx.status(204));
  }),
];
