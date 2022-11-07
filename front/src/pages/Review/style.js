import styled from "styled-components";

const RowFlexWrapper = styled.div`
  display: flex;
`;

const LeftWrapper = styled.div`
  padding: 10px;

  display: flex;
  flex-direction: column;
  gap: 10px;

  width: 350px;

  button {
    margin-bottom: 10px;
  }
`;

const RightWrapper = styled.div`
  width: calc(100vw - 350px);
`;

export { RowFlexWrapper, LeftWrapper, RightWrapper };
